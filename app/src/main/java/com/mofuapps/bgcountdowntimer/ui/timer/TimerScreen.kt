package com.mofuapps.bgcountdowntimer.ui.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.mofuapps.bgcountdowntimer.ui.theme.BGCountdownTimerTheme


@Composable
fun TimerScreen(uiState: TimerScreenUIState, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            //Text(uiState.numericalIndicator)
            NumericalIndicator(indicator = uiState.numericalIndicator, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                Button(
                    onClick = {  },
                    enabled = uiState.stage != TimerScreenStage.STAND_BY
                ) {
                    Text("キャンセル")
                }
                Spacer(modifier = Modifier.weight(1f))
                when (uiState.stage) {
                    TimerScreenStage.STAND_BY -> {
                        Button(onClick = {  }) {
                            Text("開始")
                        }
                    }
                    TimerScreenStage.RUNNING -> {
                        Button(onClick = {  }) {
                            Text("一時停止")
                        }
                    }
                    TimerScreenStage.PAUSED -> {
                        Button(onClick = {  }) {
                            Text("再開")
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

private class TimerScreenUIStateProvider: PreviewParameterProvider<TimerScreenUIState> {
    override val values: Sequence<TimerScreenUIState> = sequenceOf(
        TimerScreenUIState(
            stage = TimerScreenStage.STAND_BY,
            visualIndicator = 1f,
            numericalIndicator = "05:00"
        ),
        TimerScreenUIState(
            stage = TimerScreenStage.RUNNING,
            visualIndicator = 0.5f,
            numericalIndicator = "02:30"
        ),
        TimerScreenUIState(
            stage = TimerScreenStage.PAUSED,
            visualIndicator = 0.5f,
            numericalIndicator = "02:30"
        ),
        TimerScreenUIState(
            stage = TimerScreenStage.FINISHED,
            visualIndicator = 0f,
            numericalIndicator = "00:00"
        )
    )
}

@Preview
@Composable
private fun TimerScreenPreview(
    @PreviewParameter(provider = TimerScreenUIStateProvider::class) data: TimerScreenUIState
) {
    BGCountdownTimerTheme {
        TimerScreen(uiState = data, modifier = Modifier.fillMaxSize())
    }
}
