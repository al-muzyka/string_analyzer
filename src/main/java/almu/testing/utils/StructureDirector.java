package almu.testing.utils;

public class StructureDirector {
    private Builder builder;

    public StructureDirector(Builder builder) {
        this.builder = builder;
    }

    public void construct(String inputStr) {
        builder.splitSentenceByWordGroup(inputStr);
        builder.filterGroupsWithSmallSize(1);
        builder.sortWordsInGroup();
    }
}
