package by.aurorasoft.fuelsearcher.service.documentfactory;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentfactory.corrector.FuelDocumentCorrector;
import by.aurorasoft.fuelsearcher.service.documentfactory.loader.FuelDocumentLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class FuelDocumentFactory {
    private final FuelDocumentLoader loader;
    private final FuelDocumentCorrector corrector;

    public FuelDocument create(final String filePath) {
        final FuelDocument fuelDocument = this.loader.load(filePath);
        this.corrector.correct(fuelDocument);
        return fuelDocument;
    }
}
