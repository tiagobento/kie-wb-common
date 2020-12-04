package org.jboss.errai.validation.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import javax.validation.Validator;
import javax.validation.groups.Default;
import org.kie.workbench.common.dmn.api.definition.model.Association;
import org.kie.workbench.common.dmn.api.definition.model.AuthorityRequirement;
import org.kie.workbench.common.dmn.api.definition.model.BusinessKnowledgeModel;
import org.kie.workbench.common.dmn.api.definition.model.DMNDiagram;
import org.kie.workbench.common.dmn.api.definition.model.DMNElement;
import org.kie.workbench.common.dmn.api.definition.model.DRGElement;
import org.kie.workbench.common.dmn.api.definition.model.Decision;
import org.kie.workbench.common.dmn.api.definition.model.DecisionService;
import org.kie.workbench.common.dmn.api.definition.model.Definitions;
import org.kie.workbench.common.dmn.api.definition.model.InformationItem;
import org.kie.workbench.common.dmn.api.definition.model.InformationItemPrimary;
import org.kie.workbench.common.dmn.api.definition.model.InformationRequirement;
import org.kie.workbench.common.dmn.api.definition.model.InputClauseLiteralExpression;
import org.kie.workbench.common.dmn.api.definition.model.InputData;
import org.kie.workbench.common.dmn.api.definition.model.KnowledgeRequirement;
import org.kie.workbench.common.dmn.api.definition.model.KnowledgeSource;
import org.kie.workbench.common.dmn.api.definition.model.NamedElement;
import org.kie.workbench.common.dmn.api.definition.model.TextAnnotation;
import org.kie.workbench.common.dmn.api.property.background.BackgroundSet;
import org.kie.workbench.common.dmn.api.property.background.BgColour;
import org.kie.workbench.common.dmn.api.property.background.BorderColour;
import org.kie.workbench.common.dmn.api.property.dmn.AllowedAnswers;
import org.kie.workbench.common.dmn.api.property.dmn.Description;
import org.kie.workbench.common.dmn.api.property.dmn.DocumentationLinksHolder;
import org.kie.workbench.common.dmn.api.property.dmn.Id;
import org.kie.workbench.common.dmn.api.property.dmn.NameHolder;
import org.kie.workbench.common.dmn.api.property.dmn.QNameHolder;
import org.kie.workbench.common.dmn.api.property.dmn.Question;
import org.kie.workbench.common.dmn.api.property.font.FontColour;
import org.kie.workbench.common.dmn.api.property.font.FontFamily;
import org.kie.workbench.common.dmn.api.property.font.FontSet;
import org.kie.workbench.common.dmn.api.property.font.FontSize;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.decimalBox.definition.DecimalBoxFieldDefinition;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.integerBox.definition.IntegerBoxFieldDefinition;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.CharacterSelectorOption;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.DecimalSelectorOption;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.DefaultSelectorOption;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.EnumSelectorOption;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.IntegerSelectorOption;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.StringSelectorOption;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textArea.definition.TextAreaFieldDefinition;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.CharacterBoxFieldDefinition;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.relations.TableColumnMeta;
import org.kie.workbench.common.stunner.forms.model.ColorPickerFieldDefinition;

public class ValidatorFactoryImpl extends AbstractGwtValidatorFactory { @GwtValidation(groups = Default.class, value = { Association.class, ColorPickerFieldDefinition.class, AuthorityRequirement.class, InformationItem.class, InputClauseLiteralExpression.class, TableColumnMeta.class, Id.class, TextAnnotation.class, BorderColour.class, CharacterBoxFieldDefinition.class, KnowledgeRequirement.class, TextBoxFieldDefinition.class, DefaultSelectorOption.class, FontSize.class, Definitions.class, CharacterSelectorOption.class, BgColour.class, FontSet.class, Decision.class, BusinessKnowledgeModel.class, InformationRequirement.class, DecisionService.class, AllowedAnswers.class, DMNElement.class, Description.class, StringSelectorOption.class, EnumSelectorOption.class, DRGElement.class, IntegerSelectorOption.class, TextAreaFieldDefinition.class, Question.class, NameHolder.class, DecimalBoxFieldDefinition.class, KnowledgeSource.class, FontColour.class, DecimalSelectorOption.class, InformationItemPrimary.class, DMNDiagram.class, DocumentationLinksHolder.class, IntegerBoxFieldDefinition.class, BackgroundSet.class, QNameHolder.class, InputData.class, FontFamily.class, NamedElement.class }) public interface GwtValidator extends Validator { }
  public AbstractGwtValidator createValidator() {
    return new BeanValidator((AbstractGwtValidator) GWT.create(GwtValidator.class));
  }
}