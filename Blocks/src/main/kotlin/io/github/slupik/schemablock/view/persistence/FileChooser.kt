package io.github.slupik.schemablock.view.persistence

import java.io.File

/**
 * All rights reserved & copyright ©
 */
interface FileChooser {

    fun choseForSave(): File?
    fun choseForOpen(): File?

}