package me.xjcyan1de.cyanbot.world;

import org.apache.commons.math3.util.FastMath;

public class Location implements Cloneable {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public Location(double x, double y, double z) {
        this(x, y, z, 0, 0);
    }

    public Location(Location loc) {
        this(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    public Location(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getBlockX() {
        return (int) FastMath.floor(x);
    }

    public int getBlockY() {
        return (int) FastMath.floor(y);
    }

    public int getBlockZ() {
        return (int) FastMath.floor(z);
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void set(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.pitch = loc.getPitch();
        this.yaw = loc.getYaw();
    }

    public Location add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public boolean inRange(Location loc, double dist) {
        double dx = x - loc.getX();
        double dy = y - loc.getY();
        double dz = z - loc.getZ();
        double result = FastMath.sqrt(dx * dx + dy * dy + dz * dz);
        return result < dist;
    }

    private static final double PImult2 = 6.283185307179586D;

    /**
     * ???????????????????? ?????????????????????? ?????????????? ???? ?????????? ??????????????
     */
    public Location setDir(double x, double y, double z) {
        if ((x == 0.0D) && (z == 0.0D)) {
            this.pitch = (y > 0.0D ? -90.0F : 90.0F);
            return this;
        }
        double theta = FastMath.atan2(-x, z);
        this.yaw = ((float) Math.toDegrees((theta + PImult2) % PImult2));

        double x2 = x * x;
        double z2 = z * z;
        double xz = FastMath.sqrt(x2 + z2);
        this.pitch = ((float) FastMath.toDegrees(Math.atan(-y / xz)));
        return this;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Location clone() {
        return new Location(x, y, z);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public void add(Vector vector) {
        this.add(vector.getX(), vector.getY(), vector.getZ());
    }

    public double distanceSquared(Location loc) {
        double vecX = this.x - loc.getX();
        double vecY = this.y - loc.getY();
        double vecZ = this.z - loc.getZ();
        return vecX * vecX + vecY * vecY + vecZ * vecZ;
    }
}
