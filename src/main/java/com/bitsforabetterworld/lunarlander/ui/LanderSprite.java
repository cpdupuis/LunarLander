package com.bitsforabetterworld.lunarlander.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import com.bitsforabetterworld.lunarlander.Constants;
import com.bitsforabetterworld.lunarlander.Position;

public class LanderSprite {
	
	// the nose of the lander is at (0, 0)
	private static final int[] landerXPoints = { 0, 16, 16, 32, 24, 16, -16, -24, -32, -16, -16 };
	private static final int[] landerYPoints = { 0, 8, 16, 48, 48, 32, 32, 48, 48, 16, 8 };
	
	static Polygon landerPolygon = new Polygon(landerXPoints, landerYPoints, landerXPoints.length);;
	
	private static final int[] yellowFlameXPoints = {6, -6, 0 };
	private static final int[] yellowFlameYPoints = {32, 32, 52 };
	static Polygon yellowFlamePolygon = new Polygon(yellowFlameXPoints, yellowFlameYPoints, yellowFlameXPoints.length);
	private static final int[] redFlameXPoints = {9, -9, 0 };
	private static final int[] redFlameYPoints = {32, 32, 64 };
	static Polygon redFlamePolygon = new Polygon(redFlameXPoints, redFlameYPoints, redFlameXPoints.length);
	
	static void drawLander(Graphics2D g2, Rectangle2D windowRect, Position landerPosition, boolean isThrusterOn) {
		g2.setColor(Color.BLUE);
		double theta = landerPosition.getTheta();
		double x = windowRect.getWidth() * landerPosition.getX() / Constants.WIDTH_OF_SCREEN ;
		double y = (0.9 * windowRect.getHeight()) * ((Constants.TOP_OF_SCREEN - landerPosition.getY()) / Constants.TOP_OF_SCREEN);
		AffineTransform rotationTransform = AffineTransform.getRotateInstance(theta);
		Rectangle2D landerBounds = landerPolygon.getBounds2D();
		double translateY = y - landerBounds.getCenterY();
		double offsetY =  -landerBounds.getCenterY();
		AffineTransform translateTransform = AffineTransform.getTranslateInstance(x, translateY);
		AffineTransform offsetTransform = AffineTransform.getTranslateInstance(-landerBounds.getCenterX(), offsetY);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.concatenate(translateTransform);
		affineTransform.concatenate(rotationTransform);
		affineTransform.concatenate(offsetTransform);
		g2.transform(affineTransform);
		g2.fill(landerPolygon);
		if (isThrusterOn) {
			g2.setColor(Color.RED);
			g2.fill(redFlamePolygon);
			g2.setColor(Color.YELLOW);
			g2.fill(yellowFlamePolygon);
		}
	}

}
