package by.aurorasoft.fuelinfosearcher.service.documentcreating;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.documentcreating.contentcorrector.ParagraphContentCorrectingService;
import by.aurorasoft.fuelinfosearcher.service.documentcreating.documentloader.FuelDocumentLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class FuelDocumentCreatingService {
    private final FuelDocumentLoader documentLoader;
    private final ParagraphContentCorrectingService contentCorrector;

    public FuelDocument create() {
        final FuelDocument fuelDocument = this.documentLoader.load();
        this.contentCorrector.correct(fuelDocument);
        return fuelDocument;
    }
}
