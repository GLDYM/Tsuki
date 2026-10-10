package cn.mcmod.tsuki.client.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

final class LightBeamUtil {
    private LightBeamUtil() {}

    static void render(VertexConsumer out, Matrix4f pose, Vec3 direction, float length, float radius,
            int polygonCount, float spin, int red, int green, int blue, int alpha) {
        Vec3 axis = direction.normalize();
        Vec3 side = Math.abs(axis.y) > .99D ? new Vec3(1, 0, 0)
                : new Vec3(-axis.z, 0, axis.x).normalize();
        Vec3 other = axis.cross(side).normalize();
        Vec3 tip = axis.scale(length);
        for (int index = 0; index < polygonCount; index++) {
            double first = spin + Math.PI * 2D * index / polygonCount;
            double second = spin + Math.PI * 2D * (index + 1) / polygonCount;
            Vec3 p1 = tip.add(side.scale(Math.cos(first) * radius)).add(other.scale(Math.sin(first) * radius));
            Vec3 p2 = tip.add(side.scale(Math.cos(second) * radius)).add(other.scale(Math.sin(second) * radius));
            facet(out, pose, p1, p2, red, green, blue, alpha);
            facet(out, pose, p2, p1, red, green, blue, alpha);
        }
    }

    private static void facet(VertexConsumer out, Matrix4f pose, Vec3 first, Vec3 second,
            int red, int green, int blue, int alpha) {
        out.addVertex(pose, 0, 0, 0).setColor(red, green, blue, alpha);
        out.addVertex(pose, (float) first.x, (float) first.y, (float) first.z).setColor(red, green, blue, 0);
        out.addVertex(pose, (float) second.x, (float) second.y, (float) second.z).setColor(red, green, blue, 0);
        out.addVertex(pose, 0, 0, 0).setColor(red, green, blue, alpha);
    }
}
