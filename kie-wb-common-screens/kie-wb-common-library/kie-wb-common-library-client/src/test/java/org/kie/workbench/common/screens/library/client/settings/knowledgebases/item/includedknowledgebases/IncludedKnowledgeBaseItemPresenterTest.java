package org.kie.workbench.common.screens.library.client.settings.knowledgebases.item.includedknowledgebases;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.screens.library.client.settings.knowledgebases.item.KnowledgeBaseItemPresenter;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.kie.workbench.common.screens.library.client.settings.knowledgebases.item.KnowledgeBaseItemPresenter.IncludedKnowledgeBasesListPresenter;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IncludedKnowledgeBaseItemPresenterTest {

    private IncludedKnowledgeBaseItemPresenter includedKnowledgeBaseItemPresenter;

    @Mock
    private IncludedKnowledgeBaseItemPresenter.View view;

    @Before
    public void before() {
        includedKnowledgeBaseItemPresenter = spy(new IncludedKnowledgeBaseItemPresenter(view));
    }

    @Test
    public void testSetup() {
        includedKnowledgeBaseItemPresenter.setup("Name", mock(KnowledgeBaseItemPresenter.class));
        verify(view).init(eq(includedKnowledgeBaseItemPresenter));
        verify(view).setName(eq("Name"));
    }

    @Test
    public void testRemove() {
        final KnowledgeBaseItemPresenter parentPresenter = mock(KnowledgeBaseItemPresenter.class);
        final IncludedKnowledgeBasesListPresenter listPresenter = mock(IncludedKnowledgeBasesListPresenter.class);

        includedKnowledgeBaseItemPresenter.parentPresenter = parentPresenter;
        includedKnowledgeBaseItemPresenter.setListPresenter(listPresenter);

        includedKnowledgeBaseItemPresenter.remove();

        verify(listPresenter).remove(eq(includedKnowledgeBaseItemPresenter));
        verify(parentPresenter).fireChangeEvent();
    }
}