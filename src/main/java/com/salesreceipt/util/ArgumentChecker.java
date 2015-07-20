package com.salesreceipt.util;

import java.util.Set;

/**
 * Represents the utility class which consists of methods to perform argument
 * validation.
 */
public class ArgumentChecker
{
	public static <T> T rejectIfNull(T argument, String argumentName)
	{
		if (argument == null)
		{
			throw new IllegalArgumentException(String.format("%s cannot be null", argumentName));
		}

		return argument;
	}

	public static String rejectIfNullOrEmpty(String argument, String argumentName)
	{
		if (argument == null || argument.isEmpty())
		{
			throw new IllegalArgumentException(String.format("%s cannot be null or empty", argumentName));
		}

		return argument;
	}

	public static double rejectIfNegative(double argument, String argumentName)
	{
		if (argument < 0)
		{
			throw new IllegalArgumentException(String.format("%s cannot be less than zero", argumentName));
		}

		return argument;
	}

	public static double rejectIfZeroOrNegative(double argument, String argumentName)
	{
		if (argument <= 0)
		{
			throw new IllegalArgumentException(String.format("%s cannot be less than zero or less than zero",
					argumentName));
		}

		return argument;
	}

	public static int rejectIfZeroOrNegative(int argument, String argumentName)
	{
		if (argument <= 0)
		{
			throw new IllegalArgumentException(String.format("%s cannot be less than zero or less than zero",
					argumentName));
		}

		return argument;
	}

	public static <T> Set<T> rejectIfNullOrHasNullEntry(Set<T> argument, String argumentName)
	{
		if (argument == null)
		{
			throw new IllegalArgumentException(String.format("%s cannot be null", argumentName));
		}

		for (T singleEntry : argument)
		{
			if (singleEntry == null)
			{
				throw new IllegalArgumentException(String.format("%s cannot have null entry", argumentName));
			}
		}

		return argument;
	}

	public static int verifyIfGreaterThanZero(int argument, String argumentName)
	{
		if (argument <= 0)
		{
			throw new IllegalStateException(String.format("%s is not initialized", argumentName));
		}

		return argument;
	}

	public static double verifyIfGreaterThanZero(double argument, String argumentName)
	{
		if (argument <= 0)
		{
			throw new IllegalStateException(String.format("%s is not initialized", argumentName));
		}

		return argument;
	}

	public static double verifyIfPositive(double argument, String argumentName)
	{
		if (argument <= 0)
		{
			throw new IllegalStateException(String.format("%s is not initialized", argumentName));
		}

		return argument;
	}

	public static <T> T verifyIfInitialized(T argument, String argumentName)
	{
		if (argument == null)
		{
			throw new IllegalStateException(String.format("%s is not initialized", argumentName));
		}

		return argument;
	}

	public static double roundToTwoDecimalPlace(double argument)
	{
		return Math.round(argument * 100.0) / 100.0;
	}
}
