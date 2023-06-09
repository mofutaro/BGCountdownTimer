package com.mofuapps.bgcountdowntimer.ui.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mofuapps.bgcountdowntimer.ui.theme.BGCountdownTimerTheme

@Composable
fun TimerScreen(modifier: Modifier = Modifier, timerViewModel: TimerViewModel = hiltViewModel()) {
    val uiState by timerViewModel.uiState.collectAsState()
    TimerScreen(
        uiState = uiState,
        onStartClicked = remember { { timerViewModel.startTimer() } },
        onPauseClicked = remember { { timerViewModel.pauseTimer() } },
        onResumeClicked = remember { { timerViewModel.resumeTimer() } },
        onCancelClicked = remember { { timerViewModel.cancelTimer() } },
        modifier = modifier
    )
}

@Composable
fun TimerScreen(
    uiState: TimerScreenUIState,
    onStartClicked: () -> Unit,
    onPauseClicked: () -> Unit,
    onResumeClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("BGCountdownTimer", fontSize = 30.sp, fontWeight = FontWeight.W300)
            Spacer(modifier = Modifier.height(20.dp))
            NumericalIndicator(indicator = uiState.numericalIndicator, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                val buttonWidth = 150.dp
                Button(
                    onClick = onCancelClicked,
                    enabled = uiState.stage != TimerScreenStage.STAND_BY,
                    modifier = Modifier.width(buttonWidth)
                ) {
                    Text("キャンセル")
                }
                Spacer(modifier = Modifier.weight(1f))
                when (uiState.stage) {
                    TimerScreenStage.STAND_BY -> {
                        Button(
                            onClick = onStartClicked,
                            modifier = Modifier.width(buttonWidth)
                        ) {
                            Text("開始")
                        }
                    }
                    TimerScreenStage.RUNNING -> {
                        Button(
                            onClick = onPauseClicked,
                            modifier = Modifier.width(buttonWidth)
                        ) {
                            Text("一時停止")
                        }
                    }
                    TimerScreenStage.PAUSED -> {
                        Button(
                            onClick = onResumeClicked,
                            modifier = Modifier.width(buttonWidth)
                        ) {
                            Text("再開")
                        }
                    }
                    TimerScreenStage.FINISHED -> {
                        Button(
                            onClick = onStartClicked,
                            enabled = false,
                            modifier = Modifier.width(buttonWidth)
                        ) {
                            Text("開始")
                        }
                    }
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
        TimerScreen(
            uiState = data,
            onStartClicked = {},
            onPauseClicked = {},
            onResumeClicked = {},
            onCancelClicked = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
