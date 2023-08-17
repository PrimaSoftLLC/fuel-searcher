package by.aurorasoft.fuelinfosearcher.service.documentcreating;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.contentcorrector.FuelDocumentContentCorrector;
import by.aurorasoft.fuelinfosearcher.service.documentloader.FuelDocumentLoader;
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
