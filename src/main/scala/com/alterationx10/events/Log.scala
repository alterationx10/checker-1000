package com.alterationx10.events

import indigo.shared.events.GlobalEvent

final case class Log(message: String) extends GlobalEvent
