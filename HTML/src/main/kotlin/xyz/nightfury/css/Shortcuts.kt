/*
 * Copyright 2017 Kaidan Gustave
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.nightfury.css

import azadev.kotlin.css.Stylesheet

fun translate(tx: String, ty: String): String = "translate($tx, $ty)"
fun `var`(variable: String): String = "var($variable)"

inline fun Stylesheet.keyframes(cla: String, crossinline block: Stylesheet.() -> Unit) = at("keyframes $cla") { block() }

inline fun Stylesheet.from(crossinline block: Stylesheet.() -> Unit) = "from" { block() }
inline fun Stylesheet.to(crossinline block: Stylesheet.() -> Unit) = "to" { block() }