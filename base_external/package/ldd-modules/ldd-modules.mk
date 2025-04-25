LDD_MODULES_VERSION = '1cf8bf91e9502a122fb55d85e1f2c8217a2cc7f3'
# Note: Be sure to reference the *ssh* repository URL here (not https) to work properly
# with ssh keys and the automated build/test system.
# Your site should start with git@github.com:
LDD_MODULES_SITE = 'git@github.com:cu-ecen-aeld/assignment-7-ldmacdonald.git'
LDD_MODULES_SITE_METHOD = git
LDD_MODULES_GIT_SUBMODULES = YES

LDD_MODULES_MODULE_SUBDIRS += scull
LDD_MODULES_MODULE_SUBDIRS += misc-modules

define LDD_MODULES_INSTALL_TARGET_CMDS
	$(INSTALL) -m 0755 $(@D)/scull/scull_load $(TARGET_DIR)/usr/bin
	$(INSTALL) -m 0755 $(@D)/scull/scull_unload $(TARGET_DIR)/usr/bin
	$(INSTALL) -m 0755 $(@D)/misc-modules/module_load $(TARGET_DIR)/usr/bin
	$(INSTALL) -m 0755 $(@D)/misc-modules/module_unload $(TARGET_DIR)/usr/bin
endef

$(eval $(kernel-module))
$(eval $(generic-package))