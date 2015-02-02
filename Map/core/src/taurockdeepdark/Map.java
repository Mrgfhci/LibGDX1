package taurockdeepdark;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Matthew Brock on 18/11/2014.
 */
//http://www.youtube.com/watch?v=qik60F5I6J4
public class Map implements ApplicationListener {
    TiledMap tiledMap;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    TiledMapTileLayer[] arclCollisionLayer;
    int nMapScale;
    int nMapNumber;
    Map(int nMapNumber_,OrthographicCamera camera_) {
        nMapNumber = nMapNumber_;
        camera = camera_;
    }

    @Override
    public void create() {
        nMapScale = Gdx.graphics.getHeight() * 5 / 1080;
        tiledMap = new TmxMapLoader().load("Map" + nMapNumber + ".tmx");
        arclCollisionLayer = new TiledMapTileLayer[tiledMap.getLayers().getCount()];//The collision layer is used to get tile IDs
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, nMapScale);//Set the render to the map and scale it
        for (int i = 0; i < tiledMap.getLayers().getCount(); i++) {//Put all the layers from the map into the collision layer array
            arclCollisionLayer[i] = (TiledMapTileLayer) tiledMap.getLayers().get(i);
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.08f, 0.08f, 0.08f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
