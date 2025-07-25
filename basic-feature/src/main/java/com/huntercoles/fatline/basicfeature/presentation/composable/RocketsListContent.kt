package com.huntercoles.fatline.basicfeature.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.huntercoles.fatline.basicfeature.R
import com.huntercoles.fatline.basicfeature.presentation.model.RocketDisplayable

const val ROCKET_DIVIDER_TEST_TAG = "rocketDividerTestTag"

@Composable
fun RocketsListContent(
    rocketList: List<RocketDisplayable>,
    onRocketClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_medium),
            ),
    ) {
        itemsIndexed(
            items = rocketList,
            key = { _, rocket -> rocket.id },
        ) { index, item ->
            RocketItem(
                rocket = item,
                onRocketClick = { onRocketClick(item.wikiUrl) },
            )

            if (index < rocketList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.testTag(ROCKET_DIVIDER_TEST_TAG),
                )
            }
        }
    }
}
