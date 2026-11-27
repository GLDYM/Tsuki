package cn.mcmod.tsuki.client.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

final class LightBeamUtil {
    private LightBeamUtil() {
    }

    static void render(VertexConsumer out, Matrix4f pose, Vec3 direction, float length, float radius,
            int polygonCount, float spin, int red, int green, int blue, int alpha) {
        if (polygonCount <= 0) {
            return;
        }
        double dLen = direction.length();
        if (dLen < 1.0E-7D) {
            return;
        }
        double ax = direction.x / dLen;
        double ay = direction.y / dLen;
        double az = direction.z / dLen;

        double sx;
        double sy;
        double sz;
        if (Math.abs(ay) > 0.99D) {
            sx = 1.0D;
            sy = 0.0D;
            sz = 0.0D;
        } else {
            double sLen = Math.hypot(az, ax);
            sx = -az / sLen;
            sy = 0.0D;
            sz = ax / sLen;
        }

        double ox = ay * sz - az * sy;
        double oy = az * sx - ax * sz;
        double oz = ax * sy - ay * sx;
        double oLen = Math.sqrt(ox * ox + oy * oy + oz * oz);
        if (oLen < 1.0E-7D) {
            return;
        }
        ox /= oLen;
        oy /= oLen;
        oz /= oLen;

        double tx = ax * length;
        double ty = ay * length;
        double tz = az * length;
        double angleStep = (Math.PI * 2.0D) / polygonCount;

        double cosPrev = Math.cos(spin) * radius;
        double sinPrev = Math.sin(spin) * radius;
        float p1x = (float) (tx + sx * cosPrev + ox * sinPrev);
        float p1y = (float) (ty + sy * cosPrev + oy * sinPrev);
        float p1z = (float) (tz + sz * cosPrev + oz * sinPrev);

        for (int index = 1; index <= polygonCount; index++) {
            double angle = spin + angleStep * index;
            double cosCur = Math.cos(angle) * radius;
            double sinCur = Math.sin(angle) * radius;
            float p2x = (float) (tx + sx * cosCur + ox * sinCur);
            float p2y = (float) (ty + sy * cosCur + oy * sinCur);
            float p2z = (float) (tz + sz * cosCur + oz * sinCur);

            facet(out, pose, p1x, p1y, p1z, p2x, p2y, p2z, red, green, blue, alpha);
            facet(out, pose, p2x, p2y, p2z, p1x, p1y, p1z, red, green, blue, alpha);

            p1x = p2x;
            p1y = p2y;
            p1z = p2z;
        }
    }

    private static void facet(VertexConsumer out, Matrix4f pose,
            float x1, float y1, float z1,
            float x2, float y2, float z2,
            int red, int green, int blue, int alpha) {
        out.addVertex(pose, 0, 0, 0).setColor(red, green, blue, alpha);
        out.addVertex(pose, x1, y1, z1).setColor(red, green, blue, 0);
        out.addVertex(pose, x2, y2, z2).setColor(red, green, blue, 0);
        out.addVertex(pose, 0, 0, 0).setColor(red, green, blue, alpha);
    }
}
