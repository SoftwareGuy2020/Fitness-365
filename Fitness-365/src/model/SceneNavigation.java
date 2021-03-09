package model;

import javafx.scene.Scene;

public interface SceneNavigation
{
	/**
	 * Returns the current object as a Scene object.
	 * @return (Scene) the scene of the current object
	 */
    public Scene getView();
}

