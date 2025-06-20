package io.github.cpearl0.ctnhcore.client.renderer.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;
import org.joml.Vector3i;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public abstract class RenderUtils {
    //方向轴
    public static EnumMap<Direction, Axis> directionAxises = new EnumMap<>(Direction.class);
    static {
        directionAxises.put(Direction.NORTH, Axis.ZN);
        directionAxises.put(Direction.SOUTH, Axis.ZP);
        directionAxises.put(Direction.EAST, Axis.XP);
        directionAxises.put(Direction.WEST, Axis.XN);
        directionAxises.put(Direction.UP, Axis.YP);
        directionAxises.put(Direction.DOWN, Axis.YN);
    }
    public static EnumMap<Direction, Vector3i> directionVectors = new EnumMap<>(Direction.class);
    static {
        directionVectors.put(Direction.NORTH, new Vector3i(0, 0, -1));
        directionVectors.put(Direction.SOUTH, new Vector3i(0, 0, 1));
        directionVectors.put(Direction.EAST, new Vector3i(1, 0, 0));
        directionVectors.put(Direction.WEST, new Vector3i(-1, 0, 0));
        directionVectors.put(Direction.UP, new Vector3i(0, 1, 0));
        directionVectors.put(Direction.DOWN, new Vector3i(0, -1, 0));
    }
    public static HashMap<Vector3i,Direction> vectorDirections = new HashMap<>();
    static {
        vectorDirections.put(new Vector3i(0, 0, -1), Direction.NORTH);
        vectorDirections.put(new Vector3i(0, 0, 1), Direction.SOUTH);
        vectorDirections.put(new Vector3i(1, 0, 0), Direction.EAST);
        vectorDirections.put(new Vector3i(-1, 0, 0), Direction.WEST);
        vectorDirections.put(new Vector3i(0, 1, 0), Direction.UP);
        vectorDirections.put(new Vector3i(0, -1, 0), Direction.DOWN);
    }
    public static HashMap<Vector3i, Axis> vectorToAxis = new HashMap<>();
    static {
        vectorToAxis.put(new Vector3i(0, 0, -1), Axis.ZN);
        vectorToAxis.put(new Vector3i(0, 0, 1), Axis.ZP);
        vectorToAxis.put(new Vector3i(1, 0, 0), Axis.XP);
        vectorToAxis.put(new Vector3i(-1, 0, 0), Axis.XN);
        vectorToAxis.put(new Vector3i(0, 1, 0), Axis.YP);
        vectorToAxis.put(new Vector3i(0, -1, 0), Axis.YN);
    }
    public static Vector3i cross(Vector3i v1, Vector3i v2) {
        return new Vector3i(v1.y() * v2.z() - v1.z() * v2.y(), v1.z() * v2.x() - v1.x() * v2.z(), v1.x() * v2.y() - v1.y() * v2.x());
    }
    /**
     * 实际上是叉乘
     * @param facing 方块的实际朝向
     * @param relative 在模型中希望面对玩家的朝向
     * @return 返回模型的朝向轴,在这个轴上逆时针旋转90度就可以得到正确的模型方向
     */
    @Nullable
    public static Axis getFacingAxis(Direction facing,Direction relative) {
//        Quaternionf a = (Quaternionf) directionAxises.get(facing);
//        Quaternionf b = (Quaternionf) directionAxises.get(relative);
//        return (Axis)a.mul(b);
        //打表出奇迹
        var a = directionVectors.get(facing);
        var b = directionVectors.get(relative);
        var c = cross(a, b);
//        if(c.equals(new Vector3i(0, 0, 0)))return vectorToAxis.get(a);
        return vectorToAxis.get(c);
    }
    //这两函数可以在多方快成型判断用
    public static boolean isLeftHand(Direction controller,Direction part) {
        return getFacingAxis(part, controller) == Axis.YP;
    }
    public static boolean isRightHand(Direction controller,Direction part) {
        return getFacingAxis(part, controller) == Axis.YN;
    }

    //三角函数
    public static float sinPolynom(float x, int n){
        float powedX = x, ret = 0, sqX = x*x;
        int d=1;
        for(int i=1;i<=n;i++){
            ret += powedX/d;
            powedX *= sqX;
            d *= -(2*i+1)*(2*i);
        }
        return ret;
    }
    public static float cosPolynom(float x, int n){
        float powedX = 1, ret = 0, sqX = x*x;
        int d=1;
        for(int i=1;i<=n;i++){
            ret += powedX/d;
            powedX *= sqX;
            d *= -(2*i-1)*(2*i);
        }
        return ret;
    }
    public static float u8ToRad(byte t){
        return ((float) t /Byte.MAX_VALUE)*(2*(float)Math.PI);
    }

    public static Vector3f randVector3f(RandomSource rand) {
        int ix,iy,iz;
        float l;
        do {
            ix = rand.nextIntBetweenInclusive(Short.MIN_VALUE / 2, Short.MAX_VALUE / 2);
            iy = rand.nextIntBetweenInclusive(Short.MIN_VALUE / 2, Short.MAX_VALUE / 2);
            iz = rand.nextIntBetweenInclusive(Short.MIN_VALUE / 2, Short.MAX_VALUE / 2);
            l = (float) Math.sqrt(ix * ix + iy * iy + iz * iz);
        } while (l == 0);
        return new Vector3f(ix/l,iy/l,iz/l);
    }
    public static void autoMulPose(PoseStack stack, Direction facing, Direction original){
        Axis rot = getFacingAxis(facing, original);
        if(rot == null ){
            if(facing == original.getOpposite())
                stack.mulPose(directionAxises.get(facing.getClockWise()).rotationDegrees(180));
        }
        else{
            stack.mulPose(rot.rotationDegrees(-90));
        }
    }
    public static void autoMulPose(PoseStack stack, Direction facing){
        autoMulPose(stack, facing, Direction.SOUTH);
    }
    public static BlockPos autoRotate(BlockPos pos, Direction facing, Direction original)
    {
        Vector3i x = new Vector3i( directionVectors.get(facing.getClockWise()));
        Vector3i y = new Vector3i( directionVectors.get(Direction.UP));
        Vector3i z = new Vector3i( directionVectors.get(original));

        Vector3i ret = new Vector3i();
        ret.add(x.mul(pos.getX()));
        ret.add(y.mul(pos.getY()));
        ret.add(z.mul(pos.getZ()));
        return new BlockPos(ret.x, ret.y, ret.z);
    }
    public static BlockPos autoRotate(BlockPos pos, Direction facing)
    {
        return autoRotate(pos, facing, Direction.SOUTH);
    }
    @OnlyIn(Dist.CLIENT)
    public static float getTime(){
        return Minecraft.getInstance().level != null ?
                Minecraft.getInstance().level.getGameTime() + Minecraft.getInstance().getFrameTime() :
                0;
    }
}
