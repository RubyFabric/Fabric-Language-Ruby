package io.github.rubyfabric.language.ruby;

import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.Helpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.RubyClass;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.launch.common.FabricLauncherBase;
import net.fabricmc.loader.util.DefaultLanguageAdapter;
import java.lang.reflect.Proxy;


public class RubyAdapter extends RubyObject implements LanguageAdapter {
    private static final Ruby __ruby__ = Ruby.getGlobalRuntime();
    private static final RubyClass __metaclass__;

    static {
        String source = new StringBuilder("require 'java'\n" +
            "require 'jruby/core_ext'\n" +
            "\n" +
            "java_package 'io.github.rubyfabric.language.ruby'\n" +
            "java_import 'net.fabricmc.loader.api.LanguageAdapter'\n" +
            "java_import 'net.fabricmc.loader.api.LanguageAdapterException'\n" +
            "java_import 'net.fabricmc.loader.api.ModContainer'\n" +
            "java_import 'net.fabricmc.loader.launch.common.FabricLauncherBase'\n" +
            "java_import 'net.fabricmc.loader.util.DefaultLanguageAdapter'\n" +
            "java_import 'java.lang.reflect.Proxy'\n" +
            "\n" +
            "class RubyAdapter java_implements LanguageAdapter\n" +
            "  INSTANCE = RubyAdapter.new\n" +
            "  # TODO: Stop relying on modifying the wrapper; Figure out how to T because casting cring, even if it does work\n" +
            "  java_signature 'Object create(ModContainer, String, Class)'\n" +
            "  def create(mod, value, type)\n" +
            "    moduleField = nil\n" +
            "    begin\n" +
            "      objectClass = Class.forName(s + \"$\")\n" +
            "      moduleField = objectClass.getField(\"MODULE$\")\n" +
            "      instance = moduleField.get(null).asInstanceOf[T]\n" +
            "      if (instance == null)\n" +
            "        raise NullPointerException.new\n" +
            "      end\n" +
            "    rescue Exception => e\n" +
            "      puts \"Unable to find\" + type.getName + moduleField.getName\n" +
            "      type.newInstance\n" +
            "    end\n" +
            "  end\n" +
            "end\n" +
            "").toString();
        __ruby__.executeScript(source, "RubyAdapter.rb");
        RubyClass metaclass = __ruby__.getClass("RubyAdapter");
        if (metaclass == null) throw new NoClassDefFoundError("Could not load Ruby class: RubyAdapter");
        metaclass.setRubyStaticAllocator(RubyAdapter.class);
        __metaclass__ = metaclass;
    }

    /**
     * Standard Ruby object constructor, for construction-from-Ruby purposes.
     * Generally not for user consumption.
     *
     * @param ruby The JRuby instance this object will belong to
     * @param metaclass The RubyClass representing the Ruby class of this object
     */
    private RubyAdapter(Ruby ruby, RubyClass metaclass) {
        super(ruby, metaclass);
    }

    /**
     * A static method used by JRuby for allocating instances of this object
     * from Ruby. Generally not for user comsumption.
     *
     * @param ruby The JRuby instance this object will belong to
     * @param metaClass The RubyClass representing the Ruby class of this object
     */
    public static IRubyObject __allocate__(Ruby ruby, RubyClass metaClass) {
        return new RubyAdapter(ruby, metaClass);
    }

    /**
     * Default constructor. Invokes this(Ruby, RubyClass) with the classloader-static
     * Ruby and RubyClass instances assocated with this class, and then invokes the
     * no-argument 'initialize' method in Ruby.
     */
    public RubyAdapter() {
        this(__ruby__, __metaclass__);
        Helpers.invoke(__ruby__.getCurrentContext(), this, "initialize");
    }


    @Override @SuppressWarnings("unchecked")
    //TODO: Figure out how the hell I'm meant to get "Class<T>" into here without manually changing this...
    public <T> T create(ModContainer mod, String value, Class<T> type) {
        IRubyObject ruby_arg_mod = JavaUtil.convertJavaToRuby(__ruby__, mod);
        IRubyObject ruby_arg_value = JavaUtil.convertJavaToRuby(__ruby__, value);
        IRubyObject ruby_arg_type = JavaUtil.convertJavaToRuby(__ruby__, type);
        IRubyObject ruby_result = Helpers.invoke(__ruby__.getCurrentContext(), this, "create", ruby_arg_mod, ruby_arg_value, ruby_arg_type);
        return (T)ruby_result.toJava(Object.class);

    }

}
