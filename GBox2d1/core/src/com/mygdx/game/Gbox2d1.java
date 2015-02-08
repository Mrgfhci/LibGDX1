// Got this from:
//https://github.com/libgdx/libgdx/wiki/box2d#initialization
// It did not tell me where to put the code, so I went to my laptop project - similar, but it worked - but I did not understand it. Let's mergee the two.
// the one that worked was from:
// I got this example from http://programmersweb.blogspot.ca/2012/07/simple-libgdx-box2d-bouncing-ball.html
// I had to update "GL10" to "GL20" - that's it.

// It works: Gbox2d1: the circle falls down - right through the floor. Now I will get it to bounce like the laptop code.
// when I add the ground definition, it crashes in the ugliest way.  I will comment the area, and then give it a rest.

// now that the ground is created after the camera, there is no problem. The ball stops at the ground. Now let's make it bounce.

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Gbox2d1 extends ApplicationAdapter {
    // the laptop computer  only instantiated the world upon declaration.
    // debugRenderer and camera were the only object declared here, since they are used by both methods.
    World world = new World(new Vector2(0, -100), true); // box2d world
    //https://github.com/libgdx/libgdx/wiki/box2d#initialization says we may not need the next line.
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;


    static final float BOX_STEP=1/60f;
    static final int BOX_VELOCITY_ITERATIONS=6;
    static final int BOX_POSITION_ITERATIONS=2;
    static final float WORLD_TO_BOX=0.01f;
    static final float BOX_WORLD_TO=100f;



    @Override
    public void create() {
        // First we declare some objects.
        Body body;
        FixtureDef fixtureDef;
        CircleShape circle;
        Fixture fixture;
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        // the laptop example defined the camera more.
        camera.viewportHeight = 320;
        camera.viewportWidth = 480;
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
        camera.update();
        // create a ground to bounce off of - when added, it crashes.
        // Update - if I add the BodyDef AFTER the creation of the camera, there is no issue. See how the body requires the camera - you moron.
        BodyDef groundBodyDef =new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f); // this crashes the program. I have to look into polygons and camera settings. No longer, now that it is placed properly
        groundBody.createFixture(groundBox, 0.0f);

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyType.DynamicBody;
// Set our body's starting position in the world - laptop computer set bodyDef based on flexible camera settings. The first was hard-coded.
        bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);

        // Create our body in the world using our body definition
        body = world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        circle = new CircleShape();
        circle.setRadius(5f);
        // Create a fixture definition to apply our shape to
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1.f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 1; // Make it bounce a little bit
        body.createFixture(fixtureDef);

        // Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();
        //fixture = body.createFixture(fixtureDef);





    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(world, camera.combined);
        // the first example had the "step" variables hard-coded. Now that the steps are based on variables at the top, it works well.
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
    }
}

//https://github.com/libgdx/libgdx/wiki/box2d#initialization at Objects/Bodies part
