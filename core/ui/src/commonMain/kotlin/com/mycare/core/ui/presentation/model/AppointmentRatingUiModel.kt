package com.mycare.core.ui.presentation.model

import com.mycare.core.ui.util.ImmutableList

data class AppointmentRatingUiModel(val questionAnswers: ImmutableList<String>)

// TODO We wil need a generic QuestionAnswer sealed class because the answer can be
// Comment, 1-5 rating, Checkbox, Radio button
// Ex: { "type" : "Checkbox", "question": "Select XYZ", "options": [{"option": "A", isSelected: false}, {..}], }
