/**
 * Classe de pixel transparent
 * @author :
 * @date : 
 */

public class TransparentPixel extends AbstractPixel
{
	public int[] rgba; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	TransparentPixel()
	{
		rgba = new int[4];
		rgba[0] = 255;
		rgba[1] = 255;
		rgba[2] = 255;
		rgba[3] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	TransparentPixel(int[] rgba)
	{
		this.rgba = new int[4];
		this.rgba[0] = rgba[0];
		this.rgba[1] = rgba[1];
		this.rgba[2] = rgba[2];
		this.rgba[3] = rgba[3];
		
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		boolean pixelBool;
		int pixelVal = (rgba[0] + rgba[1] + rgba[2]) / 3;
		if (pixelVal <= 127) {
			pixelBool = false; // noir = 0
		} else {
			pixelBool = true; // blanc = 1
		}
		BWPixel bw = new BWPixel(pixelBool);
		return bw;
		
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		int pixelVal = (rgba[0] + rgba[1] + rgba[2]) / 3;
		GrayPixel gp = new GrayPixel(pixelVal);
		return gp;
		
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{

		int[] rgb = new int[3];
		rgb[0] = rgba[0]; 
		rgb[1] = rgba[1]; 
		rgb[2] = rgba[2]; 
		ColorPixel cp = new ColorPixel(rgba);
		return cp;
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public TransparentPixel Negative()
	{
		int[] rgba = new int[4];
		rgba[0] = 255 - this.rgba[0]; 
		rgba[1] = 255 - this.rgba[1]; 
		rgba[2] = 255 - this.rgba[2]; 
		rgba[3] = this.rgba[3];
		TransparentPixel cp = new TransparentPixel(rgba);
		return cp;
		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		return new TransparentPixel (this.rgba);
		
	}
	
	public void setAlpha(int alpha)
	{
		rgba[3] = alpha;
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgba[0]).toString() + " " + 
				((Integer)rgba[1]).toString() + " " +
				((Integer)rgba[2]).toString() + " " +
				((Integer)rgba[3]).toString() + " ";
	}
}
