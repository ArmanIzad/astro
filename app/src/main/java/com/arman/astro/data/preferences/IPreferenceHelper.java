package com.arman.astro.data.preferences;

import java.util.Set;

/**
 * Created by Arman on 5/15/2017.
 */

public interface IPreferenceHelper {
    void addToFavourite(String id);
    void removeFromFavourite(String id);
    Set<String> getAllFavourites();
    void clearAllFavourites();
}
