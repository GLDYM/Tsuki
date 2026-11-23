import json
import re
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parent
GUIDE_ROOT = ROOT / "src" / "main" / "resources" / "assets" / "tsuki" / "guides" / "tsuki" / "guide"
AGERATUM_ROOT = ROOT / "src" / "main" / "resources" / "assets" / "tsuki" / "ageratum"
TEXTURE_ROOT = ROOT / "src" / "main" / "resources" / "assets" / "tsuki" / "textures"
LANGS = (("", "en_us"), ("_zh_cn", "zh_cn"), ("_ja_jp", "ja_jp"))

TAG_ITEM_IMAGE = re.compile(r'<ItemImage\b[^>]*?\bid\s*=\s*"([^"]+)"[^>]*?(?:/\s*>|>\s*</ItemImage>)')
TAG_RECIPE = re.compile(r'<Recipe\b[^>]*?\bid\s*=\s*"([^"]+)"[^>]*?(?:/\s*>|>\s*</Recipe>)')
TAG_RECIPES_FOR = re.compile(r'<RecipesFor\b[^>]*?\bid\s*=\s*"([^"]+)"[^>]*?(?:/\s*>|>\s*</RecipesFor>)')
TAG_KEY_BIND = re.compile(r'<KeyBind\b[^>]*?\bid\s*=\s*"([^"]+)"[^>]*?(?:/\s*>|>\s*</KeyBind>)')
TAG_REMOVE_BLOCKS = re.compile(r'<RemoveBlocks\b[^>]*?(?:/\s*>|>\s*</RemoveBlocks>)')
GAME_SCENE = re.compile(r'<GameScene\b[^>]*>([\s\S]*?)</GameScene>')
IMPORT_STRUCTURE = re.compile(r'<ImportStructure\b[^>]*?\bsrc\s*=\s*"([^"]+)"[^>]*/?>')
BLOCK_ANNOTATION = re.compile(r'<BlockAnnotation\b[^>]*>([\s\S]*?)</BlockAnnotation>')
BLOCK = re.compile(r'<Block\b[^>]*?\bid\s*=\s*"([^"]+)"')
IMAGE = re.compile(r'!\[([^\]]*)\]\(assets/([^)]+)\)')
BR_LINE = re.compile(r'^[ \t]*(?:<br\s*/?>[ \t]*)+$', re.MULTILINE)


def list_md(directory, skip_lang_dirs):
    pages = []
    if not directory.is_dir():
        return pages
    for entry in sorted(directory.iterdir()):
        if entry.is_dir():
            if entry.name == "assets" or (skip_lang_dirs and entry.name.startswith("_")):
                continue
            pages.extend(list_md(entry, skip_lang_dirs))
        elif entry.suffix == ".md":
            pages.append(entry)
    return pages


def qualify(item_id):
    return item_id if ":" in item_id else "tsuki:" + item_id


def convert_front_matter(text):
    match = re.match(r"^---\n([\s\S]*?)\n---\n", text)
    if not match:
        return "", text
    front_matter = match.group(1)
    body = text[match.end():]
    title_match = re.search(r"^\s*title:\s*(.+?)\s*$", front_matter, re.MULTILINE)
    title = title_match.group(1).strip("\"'") if title_match else None
    items = []
    items_match = re.search(r"^item_ids:\s*\n((?:[ \t]+-.*\n?)*)", front_matter, re.MULTILINE)
    if items_match:
        for line in items_match.group(1).split("\n"):
            item_match = re.match(r"^\s*-\s*(.+?)\s*$", line)
            if item_match:
                items.append(item_match.group(1))
    head = "---\n"
    if title:
        head += "navigation:\n  title: " + json.dumps(title, ensure_ascii=False) + "\n"
    if items:
        head += "items:\n" + "\n".join("  - " + item for item in items) + "\n"
    return head + "---\n\n", body


def convert_game_scene(match):
    inner = match.group(1)
    structure = IMPORT_STRUCTURE.search(inner)
    annotations = []
    for annotation in BLOCK_ANNOTATION.findall(inner):
        paragraphs = [part.strip() for part in re.split(r"\n\s*\n", annotation) if part.strip()]
        if paragraphs:
            annotations.append(" ".join(paragraphs))
    blocks = []
    for block in BLOCK.findall(inner):
        block_id = qualify(block)
        if block_id not in blocks:
            blocks.append(block_id)
    if structure:
        result = '<structure id="' + structure.group(1) + '"/>'
    else:
        result = '<row halign="center">' + "".join('<block id="' + block_id + '"/>' for block_id in blocks) + "</row>"
    if annotations:
        result += "\n\n" + "\n\n".join("*" + text + "*" for text in annotations)
    return result


def convert_body(body):
    body = GAME_SCENE.sub(convert_game_scene, body)
    body = TAG_ITEM_IMAGE.sub(lambda m: '<item id="' + qualify(m.group(1)) + '"/>', body)
    body = TAG_RECIPE.sub(lambda m: '<recipe id="' + qualify(m.group(1)) + '"/>', body)
    body = TAG_RECIPES_FOR.sub(lambda m: '<recipe id="' + qualify(m.group(1)) + '"/>', body)
    body = TAG_KEY_BIND.sub(lambda m: '<key id="' + m.group(1) + '"/>', body)
    body = TAG_REMOVE_BLOCKS.sub("", body)
    body = IMAGE.sub(lambda m: "![" + m.group(1) + "](tsuki:guide/" + m.group(2) + ")", body)
    body = BR_LINE.sub("", body)
    body = re.sub(r"[ \t]+$", "", body, flags=re.MULTILINE)
    body = re.sub(r"\n{3,}", "\n\n", body).lstrip("\n")
    return body if body.endswith("\n") else body + "\n"


def main():
    base_pages = list_md(GUIDE_ROOT, True)
    for source_dir, language in LANGS:
        language_root = GUIDE_ROOT / source_dir if source_dir else GUIDE_ROOT
        translations = {
            page.relative_to(language_root).as_posix(): page for page in list_md(language_root, False)
        }
        output_root = AGERATUM_ROOT / language
        if output_root.exists():
            shutil.rmtree(output_root)
        for page in base_pages:
            relative = page.relative_to(GUIDE_ROOT).as_posix()
            source = translations[relative] if source_dir and relative in translations else page
            head, body = convert_front_matter(source.read_text(encoding="utf-8").replace("\r\n", "\n"))
            target = output_root / relative
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_text(head + convert_body(body), encoding="utf-8", newline="\n")
        structures = GUIDE_ROOT / "assets" / "structure"
        if structures.is_dir():
            destination = output_root / "assets" / "structure"
            destination.mkdir(parents=True, exist_ok=True)
            for structure in structures.iterdir():
                if structure.is_file():
                    shutil.copy2(structure, destination / structure.name)
    texture_dir = TEXTURE_ROOT / "guide"
    texture_dir.mkdir(parents=True, exist_ok=True)
    for image in (GUIDE_ROOT / "assets").iterdir():
        if image.is_file():
            shutil.copy2(image, texture_dir / image.name)


if __name__ == "__main__":
    main()
