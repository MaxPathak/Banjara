package src.tiles;

import src.gfx.Assets;

public class DirtTile extends Tile {

    public DirtTile(int id) {
        super(Assets.dirt, id);
    }

    
    @Override
    public boolean isSolid() {
        // TODO Auto-generated method stub
        return super.isSolid();
    }

    
}
