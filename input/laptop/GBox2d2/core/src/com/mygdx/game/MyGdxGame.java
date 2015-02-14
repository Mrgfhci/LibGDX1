// I got this example from http://programmersweb.blogspot.ca/2012/07/simple-libgdx-box2d-bouncing-ball.html
// I had to update "GL10" to "GL20" - that's it.

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    World world = new World(new Vector2(0, -100), true);
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;
    static final float BOX_STEP=1/60f;
    static final int BOX_VELOCITY_ITERATIONS=6;
    static final int BOX_POSITION_ITERATIONS=2;
    static final float WORLD_TO_BOX=0.01f;
    static final float BOX_WORLD_TO=100f;

    @Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera();
        camera.viewportHeight = 320;
        camera.viewportWidth = 480;
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
        camera.update();
        //Ground body
        BodyDef groundBodyDef =new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
        //Dynamic Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);
        Body body = world.createBody(bodyDef);
        CircleShape dynamicCircle = new CircleShape();
        dynamicCircle.setRadius(5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicCircle;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 1;
        body.createFixture(fixtureDef);
        debugRenderer = new Box2DDebugRenderer();


    }

	@Override
	public void render () {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(world, camera.combined);
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
    }
}
