require 'java'
require 'jruby/core_ext'

java_package 'io.github.rubyfabric.language.ruby'
java_import 'net.fabricmc.loader.api.LanguageAdapter'
java_import 'net.fabricmc.loader.api.LanguageAdapterException'
java_import 'net.fabricmc.loader.api.ModContainer'
java_import 'net.fabricmc.loader.launch.common.FabricLauncherBase'
java_import 'net.fabricmc.loader.util.DefaultLanguageAdapter'
java_import 'java.lang.reflect.Proxy'

class RubyAdapter java_implements net.fabricmc.loader.api.LanguageAdapter
  INSTANCE = RubyAdapter.new
  # TODO: Stop relying on modifying the wrapper; Figure out how to T because casting cring, even if it does work
  # TODO: Stop trying to get Ruby to work on block game at three fucking thirty in the morning
  java_signature 'Object create(ModContainer, String, Class)'
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
