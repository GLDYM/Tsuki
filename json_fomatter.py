import os
import json

def format_json_files(root_dir):
    for dirpath, _, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.endswith(".json"):
                file_path = os.path.join(dirpath, filename)
                try:
                    with open(file_path, "r", encoding="utf-8-sig") as f:
                        data = json.load(f)

                    with open(file_path, "w", encoding="utf-8") as f:
                        json.dump(data, f, ensure_ascii=False, indent=2, sort_keys=True)

                    print(f"Formatted: {file_path}")
                except Exception as e:
                    print(f"Error formatting {file_path}: {e}")

format_json_files("src/main/resources")
