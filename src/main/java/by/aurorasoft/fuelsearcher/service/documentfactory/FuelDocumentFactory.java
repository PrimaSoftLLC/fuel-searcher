package by.aurorasoft.fuelsearcher.service.documentfactory;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentfactory.corrector.FuelDocumentCorrector;
import by.aurorasoft.fuelsearcher.service.documentfactory.loader.FuelDocumentLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class FuelDocumentFactory {
    private final FuelDocumentLoader documentLoader;
    private final FuelDocumentCorrector contentCorrector;

    public FuelDocument create(final String filePath) {
        final FuelDocument fuelDocument = this.documentLoader.load(filePath);
        this.contentCorrector.correct(fuelDocument);
        return fuelDocument;
    }
}
