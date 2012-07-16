# Cloudbees utils Lift Module

a place to gather useful utilities for working with cloudbees

## Using this module

1. Add the following repository to your SBT project file:

    For SBT 0.11:

        resolvers += "liftmodules repository" at "http://repository-liftmodules.forge.cloudbees.com/release/"

    For SBT 0.7:

        lazy val liftModulesRelease = "liftmodules repository" at "http://repository-liftmodules.forge.cloudbees.com/release/"

2. Include this dependency:

         "net.liftmodules" %% "cloudbees-util" % (liftVersion+"VERSION")

3. In your application's Boot.boot code:

          bootstrap.liftmodules.cloudbees.util.Utils.init


