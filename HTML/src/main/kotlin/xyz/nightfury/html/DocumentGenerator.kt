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
package xyz.nightfury.html

import kotlinx.html.HTML
import kotlinx.html.TagConsumer
import kotlinx.html.html
import org.w3c.dom.Document
import java.io.File
import java.nio.file.Paths

/**
 * @author Kaidan Gustave
 */
interface DocumentGenerator {
    val file: String
    val subDir: String
        get() = ""

    fun file(base: String): File {
        return if(subDir.isNotEmpty()) Paths.get(base, subDir, "$file.html").toFile()
        else Paths.get(base, "$file.html").toFile()
    }

    fun TagConsumer<Document>.create(): Document = html { generate() }
    fun HTML.generate(): Any
}