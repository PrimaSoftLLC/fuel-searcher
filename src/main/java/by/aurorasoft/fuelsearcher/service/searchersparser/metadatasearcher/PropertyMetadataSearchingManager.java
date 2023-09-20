package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class PropertyMetadataSearchingManager {
    private final List<PropertyMetadataSearcher<?>> searchers;


}
