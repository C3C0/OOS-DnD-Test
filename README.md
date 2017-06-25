# OOS-DnD-Test
##### Show case app that demostrates how OxygenOS Alert Slider implementation breaks Android public API thus can negatively affect logic in 3rd party apps.

Top position of Alert Slider causes incorrect values of NotificationManager.getCurrentInterruptionFilter() method, depending on whether vibration for this mode is enabled or disabled.
1) when vibration is enabled - getCurrentInterruptionFilter() returns INTERRUPTION_FILTER_ALL (Ring) instead of INTERRUPTION_FILTER_ALARMS (Alarms only)
2) when vibration is disabled - getCurrentInterruptionFilter() returns INTERRUPTION_FILTER_NONE (Total silence) instead of INTERRUPTION_FILTER_ALARMS (Alarms only)

- Bottom position of Alert Slider works as expected - returns INTERRUPTION_FILTER_ALL (Ring)
- Middle positionof Alert Slider works as expected - returns INTERRUPTION_FILTER_PRIORITY (Priority only)
