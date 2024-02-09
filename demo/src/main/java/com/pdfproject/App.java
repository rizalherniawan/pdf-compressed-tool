package com.pdfproject;

import com.spire.pdf.*;
import com.spire.pdf.exporting.PdfImageInfo;
import com.spire.pdf.graphics.PdfBitmap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PdfDocument pdfFile = new PdfDocument();
        // input folder location
        pdfFile.loadFromFile("/insert/folder");

        pdfFile.getFileInfo().setIncrementalUpdate(false);
        
        for (int i = 0; i < pdfFile.getPages().getCount(); i++) {

            PdfPageBase page = pdfFile.getPages().get(i);
            PdfImageInfo[] images = page.getImagesInfo();
            if (images != null && images.length > 0)
                for (int j = 0; j < images.length; j++) {
                    PdfImageInfo image = images[j];
                    PdfBitmap bp = new PdfBitmap(image.getImage());
                    bp.setQuality(20);
                    page.replaceImage(j, bp);

                }
        }

        // Save the document to file
        pdfFile.saveToFile("output/CompressPDFcontent.pdf", FileFormat.PDF);

    }
}
