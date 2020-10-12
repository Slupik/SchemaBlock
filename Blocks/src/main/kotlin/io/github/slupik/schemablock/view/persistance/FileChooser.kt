package io.github.slupik.schemablock.view.persistance

import java.io.File

/**
 * All rights reserved & copyright ©
 */
interface FileChooser {

    fun choseForSave(): File?
    fun choseForOpen(): File?

}