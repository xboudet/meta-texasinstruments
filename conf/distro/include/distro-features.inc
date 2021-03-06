# These distro features change the build options of some components
# across the TI Linux distribution.
#
# Possible features are:
# * 720p	- Enable 720p OpenMAX components, kernel.
# * spark	- Enable Sorenson spark video decoder.
# * gfx		- Include 3D GFX module and libraries.
# * tiwifi	- Include support for TI's WLAN stack.
# * tibluetooth	- Include support for TI's Bluetooth and FM stack.
# * gps		- Include support for TI's GPS
# * testpatterns - Include OMX test patterns

# The following are standard Poky DISTRO_FEATURES, not specific to
# OMAP.  Do not touch these unless you know what you are doing.

DISTRO_FEATURES += "alsa bluetooth ext2 irda pcmcia usbgadget usbhost wifi nfs zeroconf pci"

# Below are OMAP-specific DISTRO_FEATURES.  Hack away!
# This is a multimedia-based distribution. We can specify what
# codecs to enable in this release.

# Default TI OpenMax and Camara Component. Do not remove.
DISTRO_FEATURES += "camera jpegdec jpegenc"

# Enable Sorenson spark video decoder
DISTRO_FEATURES += "spark"

# Enable 3D GFX module and libraries
DISTRO_FEATURES += "gfx"

# Enable 720p OpenMAX components
DISTRO_FEATURES += "720p"

# Enable support for TI's WLAN stack, Bluetooth and FM stack
DISTRO_FEATURES += "tibluetooth tiwifi"

# Enable support for TI's GPS
DISTRO_FEATURES += "gps"
