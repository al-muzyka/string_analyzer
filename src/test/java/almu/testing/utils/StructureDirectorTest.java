package almu.testing.utils;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class StructureDirectorTest {

    @Test
    public void shouldInvokeConstructionSteps() {
        // given
        Builder builder = mock(Builder.class);
        StructureDirector director = new StructureDirector(builder);
        String inputSentence = "input string";

        // when
        director.construct(inputSentence);

        // then
        InOrder orderVerifier = Mockito.inOrder(builder);
        orderVerifier.verify(builder, times(1)).splitSentenceByWordGroup(inputSentence);
        orderVerifier.verify(builder, times(1)).filterGroupsWithSmallSize(1);
        orderVerifier.verify(builder, times(1)).sortWordsInGroup();
    }
}
