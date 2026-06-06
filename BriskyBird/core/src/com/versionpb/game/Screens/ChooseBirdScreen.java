package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;

public class ChooseBirdScreen implements Screen {

    private final BriskyBird game;
    private Skin skin, skinVPB;
    private Stage stage;
    private Texture backGroundImage;
    private OrthographicCamera cam;
    private Viewport viewport;
    private BitmapFont font, font_TapOnBird;
    private GlyphLayout titleLayout;

    private float Screen_Width = GameInfo.GAME_WIDTH;
    private float Screen_Height = GameInfo.GAME_HEIGHT;

    private Preferences prefs;
    private String currentSelected;

    public ChooseBirdScreen(final BriskyBird game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Screen_Width / 2, Screen_Height / 2);
        cam.position.set(Screen_Width / 2, Screen_Height / 2, 0);
        viewport = new StretchViewport(Screen_Width, Screen_Height, cam);
        viewport.apply();

        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        backGroundImage = game.myassetManager.manager.get(VersionPBAssetManager.MenuBackground, Texture.class);
        font = game.myassetManager.manager.get(VersionPBAssetManager.MenuFont, BitmapFont.class);
        font_TapOnBird = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar, BitmapFont.class);
        font_TapOnBird.getData().setScale(0.6f);
        skin = game.myassetManager.manager.get(VersionPBAssetManager.shade_skin, Skin.class);
        skinVPB = game.myassetManager.manager.get(VersionPBAssetManager.vpb_skin, Skin.class);

        titleLayout = new GlyphLayout();
        titleLayout.setText(font, "Choose Your Bird");

        prefs = Gdx.app.getPreferences(GameInfo.PREFERENCES);
        currentSelected = prefs.getString("SelectedBird", "frame-1");

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.center();
        stage.addActor(rootTable);

        // Retrieve textures
        Texture classicTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrame1, Texture.class);
        Texture redTex = game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame1, Texture.class);
        Texture blueTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_blue, Texture.class);
        Texture greenTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_green, Texture.class);
        Texture blackTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_black, Texture.class);
        Texture lightBlueTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_lightblue, Texture.class);
        Texture pinkTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_pink, Texture.class);
        Texture yellowTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage, Texture.class);

        // Construct regions (extract first frame for spritesheets)
        TextureRegion classicRegion = new TextureRegion(classicTex);
        TextureRegion redRegion = new TextureRegion(redTex);
        TextureRegion blueRegion = new TextureRegion(blueTex, 0, 0, blueTex.getWidth() / 3, blueTex.getHeight());
        TextureRegion greenRegion = new TextureRegion(greenTex, 0, 0, greenTex.getWidth() / 3, greenTex.getHeight());
        TextureRegion blackRegion = new TextureRegion(blackTex, 0, 0, blackTex.getWidth() / 3, blackTex.getHeight());
        TextureRegion lightBlueRegion = new TextureRegion(lightBlueTex, 0, 0, lightBlueTex.getWidth() / 3, lightBlueTex.getHeight());
        TextureRegion pinkRegion = new TextureRegion(pinkTex, 0, 0, pinkTex.getWidth() / 3, pinkTex.getHeight());
        TextureRegion yellowRegion = new TextureRegion(yellowTex, 0, 0, yellowTex.getWidth() / 3, yellowTex.getHeight());

        // Create images
        Image classicImg = new Image(new TextureRegionDrawable(classicRegion));
        Image redImg = new Image(new TextureRegionDrawable(redRegion));
        Image blueImg = new Image(new TextureRegionDrawable(blueRegion));
        Image greenImg = new Image(new TextureRegionDrawable(greenRegion));
        Image blackImg = new Image(new TextureRegionDrawable(blackRegion));
        Image lightBlueImg = new Image(new TextureRegionDrawable(lightBlueRegion));
        Image pinkImg = new Image(new TextureRegionDrawable(pinkRegion));
        Image yellowImg = new Image(new TextureRegionDrawable(yellowRegion));

        // Create cells
        Table classicCell = createBirdCell("Classic Bird", classicImg, "frame-1");
        Table redCell = createBirdCell("Red Bird", redImg, "Redframe-1");
        Table blueCell = createBirdCell("Blue Bird", blueImg, "birdanimation_blue");
        Table greenCell = createBirdCell("Green Bird", greenImg, "birdanimation_green");
        Table blackCell = createBirdCell("Black Bird", blackImg, "birdanimation_black");
        Table lightBlueCell = createBirdCell("Light Blue Bird", lightBlueImg, "birdanimation_lightblue");
        Table pinkCell = createBirdCell("Pink Bird", pinkImg, "birdanimation_pink");
        Table yellowCell = createBirdCell("Yellow Bird", yellowImg, "birdanimation");

        // Layout the main grid (2x4 grid to fit perfectly on the 480x800 screen)
        rootTable.padTop(110); // leave space for title
        rootTable.add(classicCell).pad(10);
        rootTable.add(redCell).pad(10);
        rootTable.row();
        rootTable.add(blueCell).pad(10);
        rootTable.add(greenCell).pad(10);
        rootTable.row();
        rootTable.add(blackCell).pad(10);
        rootTable.add(lightBlueCell).pad(10);
        rootTable.row();
        rootTable.add(pinkCell).pad(10);
        rootTable.add(yellowCell).pad(10);

        rootTable.row().padTop(20);
        TextButton backBtn = new TextButton("Back", skinVPB);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new BBMainMenuScreen(game));
                dispose();
            }
        });
        rootTable.add(backBtn).colspan(2).width(200).height(50);

        rootTable.row().padTop(15);
        Label.LabelStyle bottomStyle = new Label.LabelStyle(font_TapOnBird, Color.WHITE);
        Label bottomLabel = new Label("Tap on Bird to Select", bottomStyle);
        rootTable.add(bottomLabel).colspan(2);
    }

    private Table createBirdCell(String name, Image image, final String birdId) {
        Table cell = new Table();
        cell.add(image).width(60).height(45).padBottom(5);
        cell.row();

        String displayName = name;
        if (currentSelected.equals(birdId)) {
            displayName = name + " (Selected)";
        }
        Label label = new Label(displayName, skin);
        if (currentSelected.equals(birdId)) {
            label.setColor(Color.GOLD);
        }
        cell.add(label).padBottom(5);
        cell.row();

        // Make the entire cell clickable/tappable
        cell.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateSelection(birdId);
            }
        });

        return cell;
    }

    private void updateSelection(String birdId) {
        currentSelected = birdId;
        prefs.putString("SelectedBird", birdId);
        prefs.flush();

        game.setScreen(new BirdSelectedSplashScreen(game, birdId));
        dispose();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(backGroundImage, 0, 0, Screen_Width, Screen_Height);

        // Draw Title using Menu Font
        font.getData().setScale(1.0f);
        font.draw(game.getBatch(), "Choose Your Bird", Screen_Width / 2 - titleLayout.width / 2, Screen_Height - 45);
        game.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
