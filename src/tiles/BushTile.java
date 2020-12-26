package src.tiles;

import src.gfx.Assets;

public class BushTile extends Tile {

    public BushTile(int id) {
        super(Assets.bush, id);
    }

    @Override
    public boolean isSolid() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
