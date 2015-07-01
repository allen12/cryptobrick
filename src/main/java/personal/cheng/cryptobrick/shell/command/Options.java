package personal.cheng.cryptobrick.shell.command;

import java.io.IOException;
import java.io.StringWriter;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpecBuilder;

import personal.cheng.cryptobrick.util.CryptobrickException;

/**
 * Class to specify options for a command line interface/shell
 * 
 * @author Cheng, Allen
 */
public class Options 
{
	private OptionParser parser = new OptionParser();
	private OptionSet values;

	/**
	 * See {@link #add(String, String, boolean, boolean, Object)}.
	 * 
	 * @param flag
	 * @param description
	 * @param hasArg
	 * @param required
	 * @return
	 */
	public Options add(String flag, String description, boolean hasArg,
			boolean required) 
	{
		return add(flag, description, hasArg, required, null);
	}

	/**
	 * Add an option.
	 * 
	 * @param flag
	 *            The option
	 * @param description
	 *            Description of the option
	 * @param hasArg
	 *            Whether option has an argument
	 * @param required
	 *            If the option is required
	 * @param defaultValue
	 *            Default value of argument if unspecified
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Options add(String flag, String description, boolean hasArg,
			boolean required, T defaultValue) 
	{
		if (!hasArg && required) 
		{
			throw new IllegalArgumentException("Option " + flag
					+ " cannot be required unless it has an argument");
		}

		OptionSpecBuilder builder = parser.accepts(flag, description);
		if (hasArg == true) 
		{
			ArgumentAcceptingOptionSpec<String> spec = builder
					.withRequiredArg();

			if (required) 
			{
				spec.required();
			}

			if (defaultValue != null) 
			{
				Class<T> clazz = (Class<T>) defaultValue.getClass();
				spec.ofType(clazz).defaultsTo(defaultValue);
			}
		}
		
		return this;
	}

	/**
	 * Internal: Parse arguments from command line. Throws an exception if the
	 * parsing failed for some reason.
	 * 
	 * @param args
	 */
	void parse(String[] args) 
	{
		try 
		{
			values = parser.parse(args);
		} catch (OptionException e) 
		{
			StringWriter writer = new StringWriter();
			try 
			{
				parser.printHelpOn(writer);
			} catch (IOException e1) 
			{
				throw new CryptobrickException(e1);
			}
			
			throw new CryptobrickException(e.getMessage() + System.lineSeparator()
						+ writer.toString());
		}
	}

	/**
	 * Retrieve value for the given flag. If no value was specified, return
	 * null. If no value was specified and the option has a default value,
	 * return that. This only works for options with arguments; use
	 * {@link #hasValue(String)} for boolean options.
	 * 
	 * @param flag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue(String flag) 
	{
		if (values == null) 
		{
			throw new CryptobrickException("Arguments have not yet been parsed");
		}
		
		return (T) values.valueOf(flag);
	}

	/**
	 * Whether an option with the given flag was specified
	 * 
	 * @param flag
	 * @return
	 */
	public boolean hasValue(String flag) 
	{
		if (values == null) 
		{
			throw new CryptobrickException("Arguments have not yet been parsed");
		}
		return values.has(flag);
	}

	@Override
	public String toString() 
	{
		StringWriter writer = new StringWriter();
		
		try 
		{
			parser.printHelpOn(writer);
		} catch (IOException e) 
		{
			throw new CryptobrickException(e);
		}
		
		return writer.toString();
	}
}