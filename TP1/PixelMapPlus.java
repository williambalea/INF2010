import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author Dalyna Pak et William Balea
 * @date : 19 septembre 2018
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for (int hauteur = 0; hauteur < height; hauteur++)
			for(int largeur = 0; largeur < width; largeur++)
				imageData[hauteur][largeur] = imageData[hauteur][largeur].Negative();
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for (int hauteur = 0; hauteur < height; hauteur++)
			for(int largeur = 0; largeur < width; largeur++)
				imageData[hauteur][largeur] = imageData[hauteur][largeur].toBWPixel();
		imageType = imageType.BW;
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		for (int hauteur = 0; hauteur < height; hauteur++)
			for(int largeur = 0; largeur < width; largeur++)
				imageData[hauteur][largeur] = imageData[hauteur][largeur].toGrayPixel();
		imageType = imageType.Gray;
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for (int hauteur = 0; hauteur < height; hauteur++)
			for(int largeur = 0; largeur < width; largeur++)
				imageData[hauteur][largeur] = imageData[hauteur][largeur].toColorPixel();
		imageType = imageType.Color;
	}
	
	public void convertToTransparentImage()
	{
		for (int hauteur = 0; hauteur < height; hauteur++)
			for(int largeur = 0; largeur < width; largeur++)
				imageData[hauteur][largeur] = imageData[hauteur][largeur].toTransparentPixel();
		imageType = imageType.Transparent;
	}
	
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		else {
			AbstractPixel[][] newImageData = new AbstractPixel[h][w];
			int hpercent;
			int wpercent;
			
			//on veut rapetisser l'image et prendre un pixel sur 4 par exemple.
			if (height >= h || width >= w) {
				hpercent = height/h;
				wpercent = width/w;
				for (int j = 0; j < h; j++)
					for (int i = 0; i < w; i++)
						newImageData[j][i] = imageData[j * hpercent][i * wpercent];	
			} 
			// on veut aggrandir l'image et prendre plusieurs fois le meme pixel. (1 / 2 = 0)
			else if (height < h || width < w) {
				hpercent = h/height;
				wpercent = w/width;
				for (int j = 0; j < h; j++)
					for (int i = 0; i < w; i++)
						newImageData[j][i] = imageData[j / hpercent][i / wpercent];
			}
			
			//On remplace l'ancienne image par la nouvelle 
			imageData = newImageData;
			height = h;
			width = w;
		}
		
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void insert(PixelMap pm, int row0, int col0)
	{		
		for (int j = 0; j < pm.height; j++)
			for (int i = 0; i < pm.width; i++)
				if ((j + row0 < height) || (i + col0 < width))
					imageData[j + row0][i + col0] = pm.imageData[j][i];
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w) throws IllegalArgumentException
	{
				
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		else {
			AbstractPixel[][] newImageData = new AbstractPixel[h][w];
			AbstractPixel blanc;
			switch(imageType) {
			case BW : blanc = new BWPixel(); 
					break;
			case Gray : blanc = new GrayPixel();
					break;
			case Color : blanc = new ColorPixel();
					break;
			default : blanc = new TransparentPixel();
					break;
			}
			
			for (int j = 0; j < h; j++)
				for (int i = 0; i < w; i++)
					newImageData[j][i] = blanc;
			
			for (int hauteur = 0 ; hauteur < height; hauteur++)
				for (int largeur = 0; largeur < width; largeur++)
					newImageData[hauteur][largeur] = imageData[hauteur][largeur];
			
			//On remplace l'ancienne image par la nouvelle 
			imageData = newImageData;
			height = h;
			width = w;
		}
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		AbstractPixel[][] newImageData = new AbstractPixel[height][width];
		AbstractPixel blanc;
		switch(imageType) {
		case BW : blanc = new BWPixel(); 
				break;
		case Gray : blanc = new GrayPixel();
				break;
		case Color : blanc = new ColorPixel();
				break;
		default : blanc = new TransparentPixel();
				break;
		}
		
		for (int j = 0; j < height; j++)
			for (int i = 0; i < width; i++)
				newImageData[j][i] = blanc;
		
		for (int j = 0; j < height; j++)
			for (int i = 0; i < width; i++)
				if ( (j + rowOffset) >= 0 && (i + colOffset) >= 0 && (j + rowOffset) < height && (i + colOffset) < width)
					newImageData[j + rowOffset][i + colOffset] = imageData[j][i];
		
		//on remplace l'ancienne image par la nouvelle
		imageData = newImageData;
	}
	
}
