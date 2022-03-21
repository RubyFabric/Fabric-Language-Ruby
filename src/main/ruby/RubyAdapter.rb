require 'java'
require 'jruby/core_ext'

java_package io.github.rubyfabric.language.ruby
include_class net.fabricmc.loader.api.LanguageAdapter
include_class net.fabricmc.loader.api.LanguageAdapterException
include_class net.fabricmc.loader.api.ModContainer
include_class net.fabricmc.loader.launch.common.FabricLauncherBase
include_class net.fabricmc.loader.util.DefaultLanguageAdapter
include_class java.lang.reflect.Proxy

class RubyAdapter
  include Java::LanguageAdapter
  var INSTANCE = RubyAdapter.new
  #java_signature `RubyAdapter <init>`
  def initialize
    puts "Initialized Fabric Language Ruby"
  end
  java_signature '<T> T create(ModContainer, String, Class<T>)'
  def create(mod, value, type)
    moduleField = nil
    begin
      objectClass = Class.forName(s + "$")
      moduleField = objectClass.getField("MODULE$")
      instance = moduleField.get(null).asInstanceOf[T]
      if (instance == null)
        raise NullPointerException.new
      end
    rescue Exception => e
      puts "Unable to find" + type.getName + moduleField.getName
      type.newInstance
    end
  end
end
