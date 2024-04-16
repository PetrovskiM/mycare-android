package com.mycare.core.ui.components;

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import com.mycare.core.ui.components.MCText.HeadlineLarge
import com.mycare.core.ui.components.MCText.HeadlineMedium
import com.mycare.core.ui.theme.Alpha.Divider
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceSmall
import mycare.core.ui.generated.resources.Res
import mycare.core.ui.generated.resources.navigate_back_cd
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
enum class MCToolbar {
    LargeToolbar,
    MediumToolbar;

    @Composable
    operator fun invoke(
        text: StringResource,
        extraContent: @Composable (Modifier) -> Unit = {},
        includeSpaceAfterExtraContent: Boolean = true,
        navigateBack: (() -> Unit)? = null,
        leadingButton: (@Composable () -> Unit)? = null,
    ) {
        when (this) {
            LargeToolbar -> BaseToolbar(
                text = {
                    HeadlineLarge(
                        modifier = Modifier.padding(horizontal = Space),
                        text = text,
                    )
                },
                extraContent = extraContent,
                includeSpaceAfterExtraContent = includeSpaceAfterExtraContent,
                navigateBack = navigateBack,
                leadingButton = leadingButton,
            )

            MediumToolbar -> BaseToolbar(
                text = {
                    HeadlineMedium(
                        modifier = Modifier.padding(horizontal = Space),
                        text = text,
                    )
                },
                extraContent = extraContent,
                navigateBack = navigateBack,
                leadingButton = leadingButton,
            )
        }
    }

    @Composable
    operator fun invoke(
        text: String,
        extraContent: @Composable (Modifier) -> Unit = {},
        includeSpaceAfterExtraContent: Boolean = true,
        navigateBack: (() -> Unit)? = null,
        leadingButton: (@Composable () -> Unit)? = null,
    ) {
        when (this) {
            LargeToolbar -> BaseToolbar(
                text = {
                    HeadlineLarge(
                        modifier = Modifier.padding(horizontal = Space),
                        text = text,
                    )
                },
                extraContent = extraContent,
                includeSpaceAfterExtraContent = includeSpaceAfterExtraContent,
                navigateBack = navigateBack,
                leadingButton = leadingButton,
            )

            MediumToolbar -> BaseToolbar(
                text = {
                    HeadlineMedium(
                        modifier = Modifier.padding(horizontal = Space),
                        text = text,
                    )
                },
                extraContent = extraContent,
                navigateBack = navigateBack,
                leadingButton = leadingButton,
            )
        }
    }

    @Composable
    operator fun invoke(
        text: @Composable (Modifier) -> Unit,
        extraContent: @Composable (Modifier) -> Unit = {},
        includeSpaceAfterExtraContent: Boolean = true,
        navigateBack: (() -> Unit)? = null,
        leadingButton: (@Composable () -> Unit)? = null,
    ) {
        when (this) {
            LargeToolbar -> BaseToolbar(
                text = text,
                extraContent = extraContent,
                includeSpaceAfterExtraContent = includeSpaceAfterExtraContent,
                navigateBack = navigateBack,
                leadingButton = leadingButton,
            )

            MediumToolbar -> BaseToolbar(
                text = text,
                extraContent = extraContent,
                navigateBack = navigateBack,
                leadingButton = leadingButton,
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BaseToolbar(
    text: @Composable (Modifier) -> Unit,
    extraContent: @Composable (Modifier) -> Unit = {},
    includeSpaceAfterExtraContent: Boolean = true,
    navigateBack: (() -> Unit)? = null,
    leadingButton: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = if (navigateBack != null && leadingButton != null) {
                SpaceBetween
            } else if (navigateBack != null) {
                Start
            } else {
                End
            },
        ) {
            if (navigateBack != null) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.navigate_back_cd),
                    )
                }
                leadingButton?.invoke()
            } else {
                Spacer(height = Space)
            }
        }
        if (navigateBack == null && leadingButton != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = SpaceBetween
            ) {
                text(Modifier.padding(horizontal = Space))
                leadingButton()
            }
        } else {
            text(Modifier.padding(horizontal = Space))
        }
        Spacer(height = SpaceSmall)
        extraContent(Modifier.padding(horizontal = Space))
        if (includeSpaceAfterExtraContent) {
            Spacer(height = SpaceMedium)
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = Divider))
    }
}