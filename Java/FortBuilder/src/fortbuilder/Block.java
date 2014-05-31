/**
 * Every entity that is loaded on the page will dervive from this. This is the 
 * template. When the user clicks this class will be loaded and will react
 * according to what it is. For example if it's sand then it will fall until
 * it hits another Block.
 */

package fortbuilder;

import java.awt.Color;

public class Block 
{
    int Type = 0;
    int x , y;
    Color colour = Color.BLACK;
    static int w = 10, h = 10;
    
    public Block()
    {
        
    }
    
    public Block(int Type)
    {
        this.Type = Type;
    }
}
