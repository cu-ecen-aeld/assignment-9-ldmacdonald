# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-ldmacdonald.git;protocol=ssh;branch=main \
           file://files/scull-start-stop \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "1cf8bf91e9502a122fb55d85e1f2c8217a2cc7f3"

S = "${WORKDIR}/git"

inherit module
inherit update-rc.d
INITSCRIPT_NAME = "scull-start-stop"
INITSCRIPT_PARAMS = "start 99 S ."

KERNEL_MODULE_AUTOLOAD += "scull"

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
RPROVIDES:${PN} += "kernel-module-scull"
FILES:${PN} += "${INIT_D_DIR}/${INITSCRIPT_NAME}"

do_install:append () {
    install -d ${D}${INIT_D_DIR}
    install -m 0755 ${WORKDIR}/files/${INITSCRIPT_NAME} ${D}${INIT_D_DIR}/${INITSCRIPT_NAME}