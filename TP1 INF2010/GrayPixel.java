/**
 * Classe de pixel en tons de gris
 * @author :
 * @date : 
 */

public class GrayPixel  extends AbstractPixel 
{
	int pixel; // donnee du pixel
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	GrayPixel()
	{
		this.pixel = 255;
	}
	
	/**
	 * Constructeur par parametre
	 * @param pixel : valeur du pixel
	 */
	GrayPixel(int pixel)
	{
		this.pixel = pixel;
		
	}
	
	/**
	 * Renvoie la valeur du pixel
	 */
	public int getPixel()
	{
		return pixel;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param pixel: valeur a assigner 
	 */
	public void setPixel(int pixel)
	{
		this.pixel = pixel;
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		boolean pixelVal;
		if (pixel <= 127) {
			pixelVal = false ; // noir = 0
		} else {
			pixelVal = true; // blanc = 1
		}
		BWPixel bw = new BWPixel(pixelVal);
		return bw;
		
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		
		return new GrayPixel(this.pixel) ;
		
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		int pixelVal = pixel;
		int[] rgb = new int[3];
		rgb[0] = rgb[1] = rgb[2]  = pixelVal;
		ColorPixel cp = new ColorPixel(rgb);
		return cp;
		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		int pixelVal = pixel;
		int[] rgba = new int[4];
		rgba[0] = rgba[1] = rgba[2]  = pixelVal;
		rgba[3] = 255;
		TransparentPixel cp = new TransparentPixel(rgba);
		return cp;
		
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		int pixelNeg = 255 - pixel;
		GrayPixel gp = new GrayPixel(pixelNeg);
		return gp;
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
		return ((Integer)(pixel)).toString() + " ";
	}
}
