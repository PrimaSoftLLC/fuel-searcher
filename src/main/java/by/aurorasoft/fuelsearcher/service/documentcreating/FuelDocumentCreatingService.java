package by.aurorasoft.fuelsearcher.service.documentcreating;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentcreating.contentcorrector.FuelDocumentContentCorrector;
import by.aurorasoft.fuelsearcher.service.documentcreating.documentloader.FuelDocumentLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class FuelDocumentCreatingService {
    private final FuelDocumentLoader documentLoader;
    private final FuelDocumentContentCorrector contentCorrector;

    public FuelDocument create() {
        final FuelDocument fuelDocument = this.documentLoader.load();
        this.contentCorrector.correct(fuelDocument);
        return fuelDocument;
    }
}
