package game;
import java.awt.Container;
import java.util.Stack;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
public class Display {
	private SimpleUniverse universe;
	private BranchGroup group;
	private Stack<Light> lights;
	private Stack<Primitive> shapes;
	public Display() {
		lights = new Stack<Light>();
		shapes = new Stack<Primitive>();
		universe = new SimpleUniverse();
		group = new BranchGroup();
		initObjects();
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(group);
	}
	public void run() {
		
	}
	/** Add our objects, lights, etc. to our group */
	private void initObjects() {
		BoundingSphere bound = new BoundingSphere(new Point3d(0.0f,0.0f,0.0f), 500.0);
		
		shapes.add(new Sphere(0.45f));
		
		Vector3f lightDir = new Vector3f(4.0f,-7.0f,-12.0f);
		DirectionalLight light = new DirectionalLight(new Color3f(1.8f,0.1f,0.1f), lightDir);
		light.setInfluencingBounds(bound);
		lights.add(light);
		
		
		
		
		// add them to the stacks
		for(int i = 0; i < lights.size(); i++)
			group.addChild(lights.get(i));
		for(int i = 0; i < shapes.size(); i++)
			group.addChild(shapes.get(i));
	}
	/** Loads an image texture and return a primitive */
	public Node loadObject(String imgPath, String type, Object [] params) {
		Node ret = null;
		TextureLoader loader = new TextureLoader(Roller.Path_Images+"spr_test.png", new Container());
		Texture texture = loader.getTexture();
		
		if(type.toLowerCase().trim() == "sphere") {
			ret = new Sphere(0.5f).cloneNode(true);
		} else if(type.toLowerCase().trim() == "cube") {
			ColorCube cube = new ColorCube(0.05f);
			ret = cube.cloneNode(true);
		}
		return ret;
	}
}
