package objects;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.Client;
import processing.core.PApplet;

public class Player extends GameObject implements Renderable {

    // public Rectangle player;

    // public int width;

    // public int height;

    // public float velx;

    // public float vely;

    public ArrayList<Spawnpoint> spawnlist;

    private static final long    serialVersionUID = 1L;
    public int                   r;
    public int                   g;
    public int                   b;

    public boolean               walking;

    public boolean               isColliding;

    public Player ( final int width, final int height, final int r, final int g, final int b ) {

        this.velx = 0;
        this.vely = 3;
        this.r = r;
        this.g = g;
        this.b = b;
        this.w = width;
        this.h = height;
        isColliding = false;
        walking = false;
        this.type = "player";
        spawnlist = new ArrayList<Spawnpoint>();
        Spawnpoint s1 = new Spawnpoint( 10, 838 );
        spawnlist.add( s1 );
        Spawnpoint s2 = new Spawnpoint( 110, 838 );
        spawnlist.add( s2 );
        Spawnpoint s3 = new Spawnpoint( 210, 838 );
        spawnlist.add( s3 );
        Spawnpoint cur = spawnlist.get( this.GUID % 3 );
        this.rect = new Rectangle( cur.x, cur.y, width, height );
    }

    @Override
    public void render ( final PApplet parent ) {
        parent.fill( r, g, b );
        parent.rect( rect.x, rect.y, w, h );

    }

    public void setVelx ( final float velx ) {
        this.velx = velx;
    }

    public void setVely ( final float vely ) {
        this.vely = vely;
    }

    @Override
    public void update () {
        if ( this.rect.x > Client.parentWidth ) {
            this.rect.x = -31;
        }
        if ( this.rect.x < -32 ) {
            this.rect.x = Client.parentWidth - 1;
        }
        if ( this.isColliding && jump != false ) {
            this.vely = -10;
        }
        isColliding = false;
        this.vely += gravity;
        this.rect.y += this.vely;
        if ( this.vely > 5 ) {
            this.vely = 5;
        }
        this.rect.x += this.velx;
        collides();
    }

    @Override
    public void handleMovement ( int move, int anti ) {
        if ( move != 0 ) {
            if ( move == 1 ) {
                this.velx = -3;
                walking = true;
            }
            else if ( move == 2 ) {
                this.velx = 3;
                walking = true;
            }
            else if ( move == 3 ) {
                jump = true;
            }
        }
        if ( anti != 0 ) {
            if ( anti == 1 ) {
                this.velx = 0;
                walking = false;
            }
            else if ( anti == 2 ) {
                this.velx = 0;
                walking = false;
            }
            else if ( anti == 3 ) {
                jump = false;
            }
        }
    }

}
