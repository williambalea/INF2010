/**
 * Classe de pixel en couleurs 
 * @author :
 * @date : 
 */

public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	ColorPixel(int[] rgb)
	{
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		boolean pixelBool;
		int pixelVal = (rgb[0] + rgb[1] + rgb[2]) / 3;
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
		int pixelVal = (rgb[0] + rgb[1] + rgb[2]) / 3;
		GrayPixel gp = new GrayPixel(pixelVal);
		return gp;
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		return new ColorPixel(this.rgb);
		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		int[] rgba = new int[4];
		rgba[0] = rgb[0]; 
		rgba[1] = rgb[1]; 
		rgba[2] = rgb[2]; 
		rgba[3] = 255;
		TransparentPixel cp = new TransparentPixel(rgba);
		return cp;
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		int[] rgb = new int[3];
		rgb[0] = 255 - this.rgb[0]; 
		rgb[1] = 255 - this.rgb[1]; 
		rgb[2] = 255 - this.rgb[2]; 
		ColorPixel cp = new ColorPixel(rgb);
		return cp;
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " + 
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}
}