package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

//TODO: parametrize by SubTableTitleArgument
@Component
public final class SubTableTitlePropertyMetadataSearcher extends PropertyMetadataSearcher<SubTableTitleMetadata> {

    public SubTableTitlePropertyMetadataSearcher() {
        super(SubTableTitleMetadata.class);
    }

    @Override
    protected String findPropertyName(final SubTableTitleMetadata source) {
        return null;
    }

    @Override
    protected Stream<String> findAllowableValues(List<IBodyElement> tableElements, SubTableTitleMetadata source) {
        return null;
    }

    @Override
    protected boolean isAllowableValuesDuplicated() {
        return false;
    }
}
