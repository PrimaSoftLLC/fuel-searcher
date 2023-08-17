import by.aurorasoft.fuelinfosearcher.service.documentcreating.documentloader.FuelDocumentLoader;
import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.IOException;

import static java.lang.System.out;

public final class Runner {

    public static void main(final String... args)
            throws IOException {
        final FuelDocument document1 = new FuelDocumentLoader().load();

        IBodyElement element = document1.getTables().get(10).getElements().get(0);
        out.println(element);
//        out.println(table.getRow(1).getCell(3).getText());

//        out.println(document1);
    }

    private static void showInfo(final FuelTable table) {
        out.println(table.getName());
        table.getElements().forEach(Runner::showInfo);
        out.println("*******************************************************************");
    }

    private static void showInfo(final IBodyElement element) {
        if (element instanceof XWPFParagraph paragraph) {
            out.println(paragraph.getText());
        } else {
            out.println(element);
        }
    }
}
