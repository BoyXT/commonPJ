package com.ytw.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MckEither<A, B>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MckEither.class);

    private MckEither()
    {
    }

    public abstract boolean isLeft();

    public abstract boolean isRight();

    public final LeftProjection<A, B> left()
    {
        return new LeftProjection<A, B>(this);
    }

    public final RightProjection<A, B> right()
    {
        return new RightProjection<A, B>(this);
    }

    public static final class LeftProjection<A, B>
    {
        private final MckEither<A, B> e;

        private LeftProjection(final MckEither<A, B> e)
        {
            this.e = e;
        }

        public A value()
        {
            if (e.isLeft())
            {
                return ((Left<A, B>) e).a;
            }
            else
            {
                LOGGER.error("");
                return null;
            }
        }
    }

    public static final class RightProjection<A, B>
    {
        private final MckEither<A, B> e;

        private RightProjection(final MckEither<A, B> e)
        {
            this.e = e;
        }

        public B value()
        {
            if (e.isRight())
            {
                return ((Right<A, B>) e).b;
            }
            else
            {
                LOGGER.error("");
                return null;
            }
        }
    }

    private static final class Left<A, B> extends MckEither<A, B>
    {
        private final A a;

        Left(A a)
        {
            this.a = a;
        }

        @Override
        public boolean isLeft()
        {
            return true;
        }

        @Override
        public boolean isRight()
        {
            return false;
        }
    }

    private static final class Right<A, B> extends MckEither<A, B>
    {
        private final B b;

        Right(B b)
        {
            this.b = b;
        }

        @Override
        public boolean isLeft()
        {
            return false;
        }

        @Override
        public boolean isRight()
        {
            return true;
        }
    }

    @Override
    public final boolean equals(Object obj)
    {
        if (null == obj)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }

        @SuppressWarnings("unchecked")
        MckEither<A, B> other = (MckEither<A, B>) obj;

        if (this.isLeft() && other.isLeft())
        {
            A thisA = this.left().value();
            A otherA = other.left().value();
            return thisA == otherA || null != thisA && thisA.equals(otherA);
        }
        else if (this.isRight() && other.isRight())
        {
            B thisB = this.right().value();
            B otherB = other.right().value();
            return thisB == otherB || null != thisB && thisB.equals(otherB);
        }
        else
        {
            return false;
        }
    }

    @Override
    public final int hashCode()
    {
        if (this.isLeft())
        {
            A a = this.left().value();
            return null == a ? 31 : a.hashCode();
        }
        else if (this.isRight())
        {
            B b = this.right().value();
            return null == b ? 31 : b.hashCode();
        }
        else
        {
            return 31;
        }
    }

    @Override
    public final String toString()
    {
        if (this.isLeft())
        {
            A a = this.left().value();
            return null == a ? "" : "Left(" + a.toString() + ")";
        }
        else if (this.isRight())
        {
            B b = this.right().value();
            return null == b ? "" : "Right(" + b.toString() + ")";
        }
        else
        {
            return "";
        }
    }

}
