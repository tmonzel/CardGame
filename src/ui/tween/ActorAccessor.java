package ui.tween;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ActorAccessor implements TweenAccessor<Actor> {

	public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int POSITION_XY = 3;
    public static final int SCALE_XY = 4;

    // TweenAccessor implementation

    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POSITION_X: returnValues[0] = target.getX(); return 1;
            case POSITION_Y: returnValues[0] = target.getY(); return 1;
            case POSITION_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            case SCALE_XY:
            	returnValues[0] = target.getScaleX();
                return 1;
            default: assert false; return -1;
        }
    }
    
    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_X: target.setX(newValues[0]); break;
            case POSITION_Y: target.setY(newValues[0]); break;
            case POSITION_XY:
            	target.setPosition(newValues[0], newValues[1]);           
                break;
            case SCALE_XY:
            	
            	target.setScale(newValues[0]);

            	break;
            default: assert false; break;
        }
    }


}
